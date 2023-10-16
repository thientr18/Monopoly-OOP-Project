using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerMovement : MonoBehaviour
{
    private Rigidbody2D rb;
    private BoxCollider2D coll;
    private SpriteRenderer sprite;
    private Animator animator;

    [SerializeField] private LayerMask jumpableGround;
    [SerializeField] private LayerMask wallLayer;

    private float directX = 0f;  
    [SerializeField] float moveSpeed = 8f;
    [SerializeField] float jumpForce = 14f;
    [SerializeField] float wallJumpForce = 12f;
    [SerializeField] float wallCheckDistance = 0.2f;
    public int maxJumps = 2; // Number of jumps allowed, including the initial jump.

    private int jumpsRemaining;

    private enum MovementState { idle, running, jumping, falling }

    [SerializeField] private AudioSource jumpSoundEffect;

    // Start is called before the first frame update
    private void Start()
    {
        rb = GetComponent<Rigidbody2D>();
        coll = GetComponent<BoxCollider2D>();
        sprite = GetComponent<SpriteRenderer>();
        animator = GetComponent<Animator>();

        jumpsRemaining = maxJumps;
    }

    // Update is called once per frame
    private void Update()
    {
        directX = Input.GetAxisRaw("Horizontal");
        rb.velocity = new Vector2(directX * moveSpeed, rb.velocity.y );

        if (Input.GetButtonDown("Jump") && IsGrounded())
        {
            jumpSoundEffect.Play();
            rb.AddForce(Vector2.up * jumpForce, ForceMode2D.Impulse);
        }
        if(Input.GetButtonDown("Jump") && WallJump())
        {
            jumpSoundEffect.Play();
            rb.velocity = new Vector2(rb.velocity.x, wallJumpForce);
        }

        UpdateAnimationState();
    }
    private bool IsGrounded()
    {
        return Physics2D.BoxCast(coll.bounds.center, coll.bounds.size, 0f, Vector2.down, 0.1f, jumpableGround);
    }

    private bool WallJump()
    {
        return Physics2D.BoxCast(coll.bounds.center, coll.bounds.size, 0f, Vector2.right, wallCheckDistance, wallLayer) ||
               Physics2D.BoxCast(coll.bounds.center, coll.bounds.size, 0f, Vector2.left, wallCheckDistance, wallLayer);
    }
    private void UpdateAnimationState()
    {
        MovementState state;

        if (directX > 0f)
        {
            state = MovementState.running;
            sprite.flipX = false;
        }
        else if (directX < 0f)
        {
            state = MovementState.running;
            sprite.flipX = true;
        }
        else
        {
            state = MovementState.idle;
        }

        if (rb.velocity.y > 0.1f)
        {
            state = MovementState.jumping;
        }
        if (rb.velocity.y < -0.1f)
        {
            state = MovementState.falling;
        }

        animator.SetInteger("state", (int) state);
    }
}
 